package com.example.megaport.mynews.controllers.activities

import android.app.ProgressDialog
import android.os.Bundle
import com.example.megaport.mynews.R
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.megaport.mynews.controllers.utils.MyNewsStreams
import com.example.megaport.mynews.models.Article
import com.example.megaport.mynews.safeCast
import com.example.megaport.mynews.views.FragmentAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_list_articles.*

class ListArticles : AppCompatActivity() {

    private val disposable = CompositeDisposable()

    private var section :String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_articles)

        val intent = intent
        section = intent.getStringExtra("section")

        configureRecyclerView()

        configureSwipeRefreshLayout()

        executeListRequest()

        activity_list_swipe_container.isRefreshing = false
    }

    override fun onDestroy() {
        this.disposeWhenDestroy()
        super.onDestroy()
    }

    // -----------------
    // CONFIGURATION
    // -----------------

    private fun configureRecyclerView() {
        activity_list_recycler_view.layoutManager = LinearLayoutManager(this)
        if (activity_list_recycler_view.adapter == null) {
            activity_list_recycler_view.adapter = FragmentAdapter(mutableListOf())
        }
    }

    // Will trigger a new Http request to the API when the user swipe from the top to the bottom while being at the top of the RecyclerView

    private fun configureSwipeRefreshLayout() {
        activity_list_swipe_container.setOnRefreshListener { this.executeListRequest() }
    }

    // -------------------
    // HTTP (RxJAVA)
    // -------------------

    private fun executeListRequest() {
        val mProgressDialog = ProgressDialog(this)
        mProgressDialog.isIndeterminate = true
        mProgressDialog.setMessage("Loading...")
        mProgressDialog.show()

        MyNewsStreams.streamFetchTopStories(section)
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
    }

    private fun disposeWhenDestroy() {
        disposable.clear()
    }

    // -------------------
    // UPDATE UI
    // -------------------

    private fun updateUI(res: List<Article>) {

        activity_list_recycler_view.adapter?.safeCast<FragmentAdapter> {
            it.updateData(res)
        }
        activity_list_swipe_container.isRefreshing = false
    }
    companion object{
        private const val ERROR = "Error request"
    }
}
