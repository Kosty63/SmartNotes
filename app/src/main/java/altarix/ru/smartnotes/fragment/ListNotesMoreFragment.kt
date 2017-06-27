package altarix.ru.smartnotes.fragment

import altarix.ru.smartnotes.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView

/**
 * Created by home on 27.06.2017.
 */
class ListNotesMoreFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view: View = inflater!!.inflate(R.layout.list_notes_more_frgment, null)

        var title: EditText = view.findViewById(R.id.title_more_fragment) as EditText
        title.setText(arguments.getString("title"))
        var body: EditText = view.findViewById(R.id.body_more_fragment) as EditText
        body.setText(arguments.getString("body"))
        return view
    }
}