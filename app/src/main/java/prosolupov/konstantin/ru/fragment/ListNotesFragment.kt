package prosolupov.konstantin.ru.fragment

import prosolupov.konstantin.ru.R
import prosolupov.konstantin.ru.adapter.NotesAdapter
import prosolupov.konstantin.ru.bd.JobBd
import prosolupov.konstantin.ru.model.Notes
import prosolupov.konstantin.ru.util.Swipe
import android.graphics.*
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.functions.Consumer


/**
 * Created by home on 26.06.2017.
 */
class ListNotesFragment : Fragment(), NotesAdapter.CallbackNotesAdapter{

    private var mNotesRecyclerView: RecyclerView? = null;
    private var mItems = ArrayList<Notes>();
    private var p: Paint = Paint()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createListNotes()
    }

    companion object {
        lateinit var mNotesAdapter: NotesAdapter;
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater!!.inflate(R.layout.list_notes_fragment, container, false)

        mNotesRecyclerView = view.findViewById(R.id.list_notes_recyclerView) as RecyclerView?
        mNotesRecyclerView!!.layoutManager = LinearLayoutManager(activity)
        mNotesAdapter = NotesAdapter(mItems, activity)
        mNotesRecyclerView!!.adapter = mNotesAdapter
        mNotesAdapter.setOnShareClickedListener(this)
/*        createNotes()*/
        Swipe(activity, mNotesRecyclerView!!)
/*        JobBd().testRxJava(activity)*/
        return view
    }

/*    fun editItems(context: Context, position: Int, id: Int, title: String, body: String) {
            for (i in JobBd().updateNotesBd(context, id, title, body)){
                NotesAdapter.updateItemsAdapter(position, i as Notes)
            }
            mNotesAdapter.notifyItemChanged(position)
    }*/

    override fun callbackContent(holder: NotesAdapter.ViewHolder) {
        var listNotesMore = ListNotesMoreFragment()
        var bundle = Bundle()

        bundle.putInt("position", holder.adapterPosition)
        bundle.putInt("id", mItems[holder.adapterPosition].id)
        bundle.putString("title", mItems[holder.adapterPosition].title)
        bundle.putString("body", mItems[holder.adapterPosition].body)
        listNotesMore.arguments = bundle

        fragmentManager.beginTransaction().replace(R.id.activity_list_notes, listNotesMore)
                .addToBackStack("NotesMore").commit()
    }

    fun createListNotes(){

        JobBd(activity).testRxJava().subscribe(Consumer<List<Notes>> {
            t: List<Notes> ->   mItems.addAll(t)
        })
/*        for (i in JobBd().testRxJava(activity)){
            mItems.add(i as Notes)
        }*/

    }

}