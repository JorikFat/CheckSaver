package dev.kirillbalanov.pays_sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import dev.kirillbalanov.pays_sample.databinding.FragmentDialogBinding

class CreateDialogFragment: DialogFragment() {
    lateinit var binding: FragmentDialogBinding
    companion object {
        const val TAG = "CustomDialogFragment"
        private const val KEY_TITLE = "KEY_TITLE"
        //take the title and subtitle form the Activity
        fun newInstance(title: String): CreateDialogFragment {
            val args = Bundle()
            args.putString(KEY_TITLE, title)
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
        binding = FragmentDialogBinding.inflate(layoutInflater)
        return binding.root
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
        binding.diTitle.text = arguments?.getString(KEY_TITLE)
        if (binding.diPrice.text.isEmpty()
            && binding.diQuantity.text.isEmpty()){  //сделать не пустыми, в случаем ввода
            binding.diSummary.text = "45"
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