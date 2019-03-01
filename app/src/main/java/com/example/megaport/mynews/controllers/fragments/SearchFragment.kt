package com.example.megaport.mynews.controllers.fragments

import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.megaport.mynews.controllers.activities.WebViewActivity
import com.example.megaport.mynews.controllers.utils.ItemClickSupport
import com.example.megaport.mynews.controllers.utils.MyNewsStreams
import com.example.megaport.mynews.models.Article
import com.example.megaport.mynews.models.Result
import com.example.megaport.mynews.R
import com.example.megaport.mynews.views.SearchAdapter
import com.example.megaport.mynews.controllers.utils.safeCast
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_main.*

class SearchFragment : androidx.fragment.app.Fragment() {

    private val disposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    private var query = ""
    private var startDate = ""
    private var endDate = ""
    private var section = ""

    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments
        if (bundle != null) {
            query = bundle.getString("query", null)
            startDate = bundle.getString("sDate", null)
            endDate = bundle.getString("eDate", null)
            section = bundle.getString("section", "type_of_material:News")

        }

        configureRecyclerView()

        configureSwipeRefreshLayout()

        configureOnClickRecyclerView()

        executeHttpRequestWithRetrofit()

        fragment_main_swipe_container.isRefreshing = false
    }

    override fun onDestroy() {
        disposeWhenDestroy()
        super.onDestroy()

    }

    // -----------------
    // ACTION
    // -----------------

    private fun configureOnClickRecyclerView() {
        ItemClickSupport.addTo(fragment_main_recycler_view, R.layout.fragment_main_item).setOnItemClickListener { _, position, _ ->
            fragment_main_recycler_view.adapter?.safeCast<SearchAdapter> {
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

    // Configure the RecyclerView by creating a new adapter object and attaching it to the RecyclerView

    private fun configureRecyclerView() {
        fragment_main_recycler_view.layoutManager = LinearLayoutManager(activity)
        if (fragment_main_recycler_view.adapter == null) {
            fragment_main_recycler_view.adapter = SearchAdapter(mutableListOf())
        }
    }

    // Will trigger a new Http Request when the user swipe

    private fun configureSwipeRefreshLayout() {
        fragment_main_swipe_container.setOnRefreshListener { this.executeHttpRequestWithRetrofit() }
    }

    // -------------------
    // HTTP (RxJAVA)
    // -------------------

    // Will execute a Http request with RxJAVA and Retrofit

    private fun executeHttpRequestWithRetrofit() {
        val mProgressDialog = ProgressDialog(activity)
        mProgressDialog.isIndeterminate = true
        mProgressDialog.setMessage("Loading...")
        mProgressDialog.show()
        MyNewsStreams.streamFetchSearch(query, startDate, endDate, section).subscribeWith(object : DisposableObserver<Result>() {
            override fun onNext(result: Result) {
                updateUI(result.articles?.articles ?: emptyList())
            }

            override fun onError(e: Throwable) {}

            override fun onComplete() {
                mProgressDialog.dismiss()
            }
        }).addTo(disposable)
    }

    private fun disposeWhenDestroy() {
        this.disposable.clear()
    }

    // -------------------
    // UPDATE UI
    // -------------------

    private fun updateUI(res: List<Article>) {

        fragment_main_recycler_view.adapter?.safeCast<SearchAdapter> {
            it.updateData(res)
        }
        fragment_main_swipe_container.isRefreshing = false
    }

}
