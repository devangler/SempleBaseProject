package com.sempleprojectbase.fragment.base

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.sempleprojectbase.MainActivity
import com.sempleprojectbase.R
import com.sempleprojectbase.koin.DIComponent

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BaseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
abstract class BaseFragment<T : ViewDataBinding>(@LayoutRes private val layoutId: Int) : BaseNavFragment() {

    /**
     * These properties are only valid between onCreateView and onDestroyView
     * @property binding
     *          -> after onCreateView
     *          -> before onDestroyView
     */
    private var _binding: T? = null
    val binding get() = _binding!!

    /**
     * These properties are only valid between onCreateView and onDestroyView
     * @property globalContext
     * @property globalActivity
     * @property mainActivity
     *          -> after onCreateView
     *          -> before onDestroyView
     */

    val globalContext by lazy { binding.root.context }
    val globalActivity by lazy { globalContext as Activity }
    val mainActivity by lazy { globalActivity as MainActivity }

    private var hasInitializedRootView = false
    private var rootView: View? = null

    protected val diComponent by lazy { DIComponent() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        rootView?.let {
            _binding = DataBindingUtil.bind(it)
            (it.parent as? ViewGroup)?.removeView(rootView)
            return it
        } ?: kotlin.run {
            _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
            rootView = binding.root
            return binding.root
        }
    }

    /**
     *      Use the following method in onViewCreated from escaping reinitializing of views
     *      if (!hasInitializedRootView) {
     *          hasInitializedRootView = true
     *          // Your Code...
     *      }
     */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!hasInitializedRootView) {
            hasInitializedRootView = true
            onViewCreatedOneTime()
        }
        onViewCreatedEverytime()
    }

    /**
     *  @since : Write code to be called onetime...
     */
    abstract fun onViewCreatedOneTime()

    /**
     *  @since : Write code to be called everytime...
     */
    abstract fun onViewCreatedEverytime()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        hasInitializedRootView = false
        rootView = null
    }
}