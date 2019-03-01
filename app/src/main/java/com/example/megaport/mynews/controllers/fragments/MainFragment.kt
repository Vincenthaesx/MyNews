package com.example.megaport.mynews.controllers.fragments

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.megaport.mynews.controllers.activities.WebViewActivity
import com.example.megaport.mynews.controllers.utils.ItemClickSupport
import com.example.megaport.mynews.controllers.utils.MyNewsStreams
import com.example.megaport.mynews.models.Article
import com.example.megaport.mynews.R
import com.example.megaport.mynews.views.FragmentAdapter
import com.example.megaport.mynews.controllers.utils.safeCast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : androidx.fragment.app.Fragment() {

    private val disposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    private var position: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(POSITION)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureRecyclerView()

        configureSwipeRefreshLayout()

        configureOnClickRecyclerView()

        executeRequestArticles()

        fragment_main_swipe_container.isRefreshing = false
    }

    override fun onDestroy() {
        this.disposeWhenDestroy()
        super.onDestroy()
    }

    // -----------------
    // ACTION
    // -----------------

    private fun configureOnClickRecyclerView() {
        ItemClickSupport.addTo(fragment_main_recycler_view, R.layout.fragment_main_item).setOnItemClickListener { _, position, _ ->
            fragment_main_recycler_view.adapter?.safeCast<FragmentAdapter> {
                val article = it.getApplications(position)
                val intent = Intent(context, WebViewActivity::class.java)
                intent.putExtra("Url", article.url)
                startActivity(intent)
            }
        }
    }

    // -----------------
    // CONFIGURATION
    // -----------------
    private fun configureRecyclerView() {
        fragment_main_recycler_view.layoutManager = LinearLayoutManager(activity)
        if (fragment_main_recycler_view.adapter == null) {
            fragment_main_recycler_view.adapter = FragmentAdapter(mutableListOf())
        }
    }

    private fun configureSwipeRefreshLayout() {
        fragment_main_swipe_container.setOnRefreshListener { this.executeRequestArticles() }
    }

    // -------------------
    // HTTP (RxJAVA)
    // -------------------

    private fun executeRequestArticles() {
        val mProgressDialog = ProgressDialog(activity)
        mProgressDialog.isIndeterminate = true
        mProgressDialog.setMessage("Loading...")
        mProgressDialog.show()
        when (position) {
            0 -> MyNewsStreams.streamFetchTopStories(HOME)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        updateUI(it.articles ?: emptyList())
                    }, {
                        it.printStackTrace()
                        Log.e("TAG", ERROR)
                    }, {
                        mProgressDialog.dismiss()
                    }).addTo(disposable)

            1 -> MyNewsStreams.streamFetchMostPopular()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        updateUI(it.articles ?: emptyList())
                    }, {
                        it.printStackTrace()
                        Log.e("TAG", ERROR)
                    }, {
                        mProgressDialog.dismiss()
                        fragment_main_swipe_container.isRefreshing = false
                    }).addTo(disposable)

            2 -> MyNewsStreams.streamFetchTopStories(POLITICS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            updateUI(it.articles ?: emptyList())
                        }, {
                            it.printStackTrace()
                            Log.e("TAG", ERROR)
                        }, {
                            mProgressDialog.dismiss()
                            fragment_main_swipe_container.isRefreshing = false
                        }).addTo(disposable)
        }
    }

    private fun disposeWhenDestroy() {
        this.disposable.clear()
    }

    // -------------------
    // UPDATE UI
    // -------------------

    private fun updateUI(res: List<Article>) {

        fragment_main_recycler_view.adapter?.safeCast<FragmentAdapter> {
            it.updateData(res)
        }
        fragment_main_swipe_container.isRefreshing = false
    }


    companion object {

        private const val POSITION = "position"
        private const val HOME = "home"
        private const val POLITICS = "politics"
        private const val ERROR = "error request"

        fun newInstance(position: Int): MainFragment {
            val fragment = MainFragment()
            val bundle = Bundle().apply {
                putInt(POSITION, position)
            }
            fragment.arguments = bundle
            return fragment

        }
    }
}