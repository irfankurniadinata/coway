package com.example.coway.core

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.coway.ui.custom.CowayToast
import com.example.coway.ui.custom.DialogProgress
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {

    /**
     * This variable is used for showing toast error internet, 500, etc
     */
    protected var cowayToast: CowayToast? = null

    /**
     * This variable is used for binding the view
     */
    protected lateinit var binding: B

    /**
     * This function is used for set the view layout
     */
    @LayoutRes
    protected abstract fun getResLayoutId(): Int

    private var progressDialog: DialogProgress? = null

    /**
     * This function is used for set the action when the activity was created
     */
    protected abstract fun onActivityCreated(savedInstanceState: Bundle?)

    /**
     * This function is used for init all function when activity is created
     * There're have function for binding the view with data binding
     * There're also have function for listen the connection state change
     * and have function to observe the error state
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<B>(this, getResLayoutId())
            .apply {
                lifecycleOwner = this@BaseActivity
            }

        cowayToast = CowayToast(this)
        progressDialog = DialogProgress(this)

        onActivityCreated(savedInstanceState)

    }

    fun showToastInfo(message: String?) {
        if (message.isNullOrEmpty()) return
        cowayToast?.setDismiss(CowayToast.LENGTH_SHORT)?.showInfo(message)
    }

    fun showToastInfo(message: String?, callback: CowayToast.Callback?) {
        if (message.isNullOrEmpty()) return
        cowayToast?.setDismiss(CowayToast.LENGTH_SHORT, callback)?.showInfo(message)
    }

    fun showToastInfo(message: String?, duration: Long, callback: CowayToast.Callback?) {
        if (message.isNullOrEmpty()) return
        cowayToast?.setDismiss(duration, callback)?.showInfo(message)
    }

    fun showToastDanger(message: String?) {
        if (message.isNullOrEmpty()) return
        cowayToast?.showDanger(message)
    }

    fun showToastDanger(message: String?, duration: Long, callback: CowayToast.Callback?) {
        if (message.isNullOrEmpty()) return
        cowayToast?.setDismiss(duration, callback)?.showDanger(message)
    }

    fun hideToast() {
        try {
            cowayToast?.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun onSetMessage(info: String? = null, error : String? = null) {
        info?.let {
            showToastInfo(info)
        }
        error?.let {
            showToastDanger(error)
        }

    }

    fun onSetProgress(isShow : Boolean){
        if (isShow) showProgress()
        else hideProgress()
    }

    fun showProgress(): DialogProgress? {
        try {
            progressDialog?.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return progressDialog
    }

    fun hideProgress() {
        try {
            progressDialog?.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * This is used for unregister the listener form network change when activity is destroyed
     */
    override fun onDestroy() {
        try {
            cowayToast?.dismiss()
            progressDialog?.dismiss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        super.onDestroy()
    }

}