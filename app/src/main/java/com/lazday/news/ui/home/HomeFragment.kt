package com.lazday.news.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.lazday.news.R
import com.lazday.news.data.news.ArticleModel
import com.lazday.news.data.news.CategoryModel
import com.lazday.news.databinding.CustomToolbarBinding
import com.lazday.news.databinding.FragmentHomeBinding
import com.lazday.news.ui.detail.DetailActivity
import com.lazday.news.ui.news.CategoryAdapter
import com.lazday.news.ui.news.NewsAdapter
import org.koin.androidx.scope.newScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module
import timber.log.Timber

val homeModule = module {
    factory { HomeFragment() }
}

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var bindingToolbar: CustomToolbarBinding
    private val viewModel: HomeViewModel by viewModel()

    private val newsAdapter by lazy {
        NewsAdapter(arrayListOf(), object : NewsAdapter.OnAdapterListener {
            override fun onClick(article: ArticleModel) {
                startActivity(
                    Intent(requireActivity(), DetailActivity::class.java)
                        .putExtra("intent_detail", article)
                )
            }
        })
    }

    private val categoryAdapter by lazy {
        CategoryAdapter(viewModel.categories, object : CategoryAdapter.OnAdapterListener {
            override fun onClick(category: CategoryModel) {
                viewModel.category.postValue(category.id)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        bindingToolbar = binding.toolbar
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingToolbar.title = viewModel.title
        bindingToolbar.container.inflateMenu(R.menu.menu_search)
        val menu = binding.toolbar.container.menu
        val search = menu.findItem(R.id.action_search)
        val searchView = search.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                firstLoadData()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    viewModel.query = it
                }
                return true
            }

        })

        /**
         * lifecycleOwner di gunakan untuk menginisialisasikan data binding untuk type yang berbentuk ViewModel
         */
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.listCategory.adapter = categoryAdapter
        binding.listNews.adapter = newsAdapter

        viewModel.category.observe(viewLifecycleOwner, Observer {
            NewsAdapter.VIEW_TYPES = if(it.isEmpty()) 1 else 2
            firstLoadData()
        })

        viewModel.news.observe(viewLifecycleOwner, Observer {
            Timber.e(it.articles.toString())
            if(viewModel.page == 1) newsAdapter.clear()
            newsAdapter.add(it.articles)
        })

        viewModel.message.observe(viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        })

        /**
         * tanda ( - ) : merupakan parameter yang tidak di gunakan
         */
        binding.scroll.setOnScrollChangeListener { v: NestedScrollView, _, scrollY, _, _ ->
            if (scrollY == v?.getChildAt(0)!!.measuredHeight - v.measuredHeight){
                if (viewModel.page <=  viewModel.total && viewModel.loadingMore.value == false)
                    viewModel.fetch()
            }
        }
    }

    private fun firstLoadData() {
        binding.scroll.scrollTo(0, 0)
        viewModel.page = 1
        viewModel.total = 1
        viewModel.fetch()
    }
}