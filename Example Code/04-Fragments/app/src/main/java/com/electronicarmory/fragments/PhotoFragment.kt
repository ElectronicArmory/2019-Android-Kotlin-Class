package com.electronicarmory.fragments

import android.content.Context
import android.os.Bundle
import android.app.Fragment
import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.electronicarmory.fragments.Events.EventDatabaseAdd

import com.electronicarmory.fragments.dummy.PhotoContent
import com.electronicarmory.fragments.dummy.PhotoContent.PhotoItem
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [PhotoFragment.OnListFragmentInteractionListener] interface.
 */
class PhotoFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null



    private lateinit var mAdapter:MyPhotoRecyclerViewAdapter

    private lateinit var recyclerView:RecyclerView

    private lateinit var addToDoButton:Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_photo_list, container, false)

        addToDoButton = view.findViewById(R.id.addToDoButton)

        addToDoButton.setOnClickListener {
            showAddToDo()
        }

        recyclerView = view.findViewById(R.id.photoRecyclerView)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyPhotoRecyclerViewAdapter(PhotoContent.ITEMS, listener)
                mAdapter = adapter as MyPhotoRecyclerViewAdapter
            }
        }

        EventBus.getDefault().register(this)

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun addedItem(event: EventDatabaseAdd){
        mAdapter.notifyDataSetChanged()
    }


    private fun showAddToDo(){
        val addToDoIntent = Intent(activity, AddToDoActivity::class.java)

        activity.startActivityForResult(addToDoIntent, 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if( data != null ){
            val newTodo:ToDoItem = data.getSerializableExtra("NEW_TODO") as ToDoItem
            Log.d("BSU",  newTodo.todoTitle)
        }
        mAdapter.notifyDataSetChanged()
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: PhotoItem?)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            PhotoFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
