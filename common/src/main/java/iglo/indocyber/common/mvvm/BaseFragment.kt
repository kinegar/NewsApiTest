package iglo.indocyber.common.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import iglo.indocyber.common.BR

abstract class BaseFragment<VM: BaseViewModel, Binding: ViewDataBinding> : Fragment(){
    abstract val vm:VM
    lateinit var binding: Binding
    abstract val layoutResourceId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<Binding>(inflater, layoutResourceId, container, false)
        binding.setVariable(BR.vm,vm)
        binding.lifecycleOwner = this
        initBinding(binding)
        return binding.root
    }



    open fun initBinding(binding: Binding) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parentFragment?.let {
            if(it is BaseFragment<*,*>){
                vm.parent = it.vm
            }
        }
        vm.navigationtEvent.observe(this) {
            findNavController().navigate(it)
        }
        vm.showDialogEvent.observe(this) {
            AlertDialog.Builder(requireContext()).setTitle(
                it.title
            ).setMessage(it.message).apply {
                it.positiveButton?.let {
                    setPositiveButton(it.first) { dialog, flag ->
                        it.second.invoke()
                    }
                }
                it.negativeButton?.let {
                    setNegativeButton(it.first) { dialog, flag ->
                        it.second.invoke()
                    }
                }
            }.create().show()
        }
        vm.popBackStackEvent.observe(this) {
            findNavController().popBackStack()
        }
    }
}