package altarix.ru.smartnotes.fragment

import altarix.ru.smartnotes.R
import altarix.ru.smartnotes.adapter.NotesAdapter
import altarix.ru.smartnotes.model.Notes
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup




/**
 * Created by home on 26.06.2017.
 */
class ListNotesFragment : Fragment(), NotesAdapter.CallbackNotesAdapter{

    private var mNotesRecyclerView: RecyclerView? = null;
    private var mItems = ArrayList<Notes>();
    var mNotesAdapter: NotesAdapter? = null;

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater!!.inflate(R.layout.list_notes_fragment, container, false)
        var notes: Notes

        var i = 0
        while (i < 10) {

            var notes: Notes = Notes(i, "Hello ${i}", "Тело ${i}");
            notes
            mItems.add(notes)
            i++

        }

        mNotesRecyclerView = view.findViewById(R.id.list_notes_recyclerView) as RecyclerView?
        mNotesRecyclerView!!.layoutManager = LinearLayoutManager(activity)
        mNotesAdapter = NotesAdapter(mItems, activity)
        mNotesRecyclerView!!.adapter = mNotesAdapter
        mNotesAdapter!!.setOnShareClickedListener(this)

        return view
    }

    override fun callbackContent(holder: NotesAdapter.ViewHolder) {
        var listNotesMore = ListNotesMoreFragment()
        var bundle = Bundle()
        bundle.putString("title", mItems.get(holder.adapterPosition).title)
        bundle.putString("body", mItems.get(holder.adapterPosition).body)
        listNotesMore.arguments = bundle
        fragmentManager.beginTransaction().replace(R.id.activity_list_notes, listNotesMore)
                .addToBackStack("NotesMore").commit()

    }
}