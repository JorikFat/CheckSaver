package dev.kirillbalanov.pays_sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class CreateDialogFragment: DialogFragment() {

    companion object {

        const val TAG = "CustomDialogFragment"

        private const val KEY_TITLE = "KEY_TITLE"
        private const val KEY_SUBTITLE = "KEY_SUBTITLE"

        //take the title and subtitle form the Activity
        fun newInstance(title: String, subTitle: String): CreateDialogFragment {
            val args = Bundle()
            args.putString(KEY_TITLE, title)
            args.putString(KEY_SUBTITLE, subTitle)
            val fragment = CreateDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupClickListeners(view)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    //setting the text in CustomDialog
    private fun setupView(view: View) {
        view.findViewById<TextView>(R.id.tvTitle).text = arguments?.getString(KEY_TITLE)
        view.findViewById<TextView>(R.id.tvSubTitle).text = arguments?.getString(KEY_SUBTITLE)
        if (view.findViewById<EditText>(R.id.quantity).text.isEmpty() && view.findViewById<EditText>(R.id.price).text.isEmpty()){  //сделать не пустыми, в случаем ввода
            view.findViewById<TextView>(R.id.summary).text = "45"
        }
    }

    //setting all the click listeners of the CustomDialog
    private fun setupClickListeners(view: View) {

        // On clicking the positive/negative button,
        // the dialog will be closed with the help of dismiss()
//        view.btnPositive.setOnClickListener {
//            dismiss()
//        }
//        view.btnNegative.setOnClickListener {
//            dismiss()
//        }
    }
}