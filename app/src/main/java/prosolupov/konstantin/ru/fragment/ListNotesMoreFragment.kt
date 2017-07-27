package prosolupov.konstantin.ru.fragment

import prosolupov.konstantin.ru.R
import prosolupov.konstantin.ru.adapter.NotesAdapter
import prosolupov.konstantin.ru.model.Notes
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText


/**
 * Created by home on 27.06.2017.
 */
class ListNotesMoreFragment : Fragment() {

    private var titleEditText: EditText? = null
    private var bodyEditText: EditText? = null
    private var savedButton: Button? = null
    private var editButton: Button? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view: View = inflater!!.inflate(R.layout.list_notes_more_frgment, null)
        titleEditText = view.findViewById(R.id.title_more_fragment) as EditText
        bodyEditText = view.findViewById(R.id.body_more_fragment) as EditText
        savedButton = view.findViewById(R.id.saved_notes) as Button
        editButton = view.findViewById(R.id.edit_button) as Button

        if (arguments != null){

            titleEditText?.setText(arguments.getString("title"))
            bodyEditText?.setText(arguments.getString("body"))

            titleEditText?.isEnabled = false
            bodyEditText?.isEnabled = false

        }

        savedNotes()
        editNotes()
        return view

    }

    private fun savedNotes(){

        var title: String
        var body: String
        var id: Int
        var position: Int

        savedButton?.setOnClickListener {

            title = titleEditText?.editableText.toString()
            body = bodyEditText?.editableText.toString()

            //Обновление события
            if (arguments != null){
                id = arguments["id"] as Int
                position = arguments["position"] as Int
                NotesAdapter.updateItemsAdapter(position, Notes(title, body))
/*                JobBd().updateNotesBd(activity, id, title, body)*/
                /*ListNotesFragment().editItems(activity, position, id, title, body)*/


            }else{
                NotesAdapter.addItemsAdapter(Notes(title, body))
                /*JobBd().addNotesBd(activity, title, body)*/

            }
        }
    }

    private fun editNotes(){
        editButton?.setOnClickListener {
            titleEditText?.isEnabled = true
            bodyEditText?.isEnabled = true

        }
    }

}


