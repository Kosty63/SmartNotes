package prosolupov.konstantin.ru.util

import prosolupov.konstantin.ru.R
import android.content.Context
import android.graphics.*
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper

/**
 * Created by home on 12.07.2017.
 */
class Swipe constructor(context: Context, mNotesRecyclerView: RecyclerView) {

    private var p: Paint = Paint()

    val simpleItemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition

            if (direction == ItemTouchHelper.LEFT) {
                //mNotesAdapter.removeItem(position)
            } else {
/*
                    removeView()
                    edit_position = position
                    alertDialog.setTitle("Edit Country")
                    et_country.setText(countries.get(position))
                    alertDialog.show()*/

            }
        }

        override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                 dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {

            var icon: Bitmap
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                val itemView = viewHolder.itemView
                val height = itemView.bottom.toFloat() - itemView.top.toFloat()
                val width = height / 3

                if (dX > 0) {
                    p.setColor(Color.parseColor("#388E3C"))
                    val background = RectF(itemView.left.toFloat(), itemView.top.toFloat(), dX, itemView.bottom.toFloat())
                    c.drawRect(background, p)
                    icon = BitmapFactory.decodeResource(context.resources, R.drawable.ic_delete_white2)
                    val icon_dest = RectF(itemView.left.toFloat() + width, itemView.top.toFloat() + width, itemView.left.toFloat() + 2 * width, itemView.bottom.toFloat() - width)
                    c.drawBitmap(icon, null, icon_dest, p)
                } else {
                    p.setColor(Color.parseColor("#D32F2F"))
                    val background = RectF(itemView.right.toFloat() - dX, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
                    c.drawRect(background, p)
                    icon = BitmapFactory.decodeResource(context.resources, R.drawable.ic_delete_white2)
                    val icon_dest = RectF(itemView.right.toFloat() - 2 * width, itemView.top.toFloat() + width, itemView.right.toFloat() - width, itemView.bottom.toFloat() - width)
                    c.drawBitmap(icon, null, icon_dest, p)
                }
            }
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }
    }

    val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
    val i = itemTouchHelper.attachToRecyclerView(mNotesRecyclerView)

}