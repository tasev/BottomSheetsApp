package com.animations.myanimapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class DummyDetailsFragmentOne : Fragment() {

  val TAG = "DummyDetailsFragmentOne"

  //2
  companion object {

    fun newInstance(): DummyDetailsFragmentOne {
      return DummyDetailsFragmentOne()
    }
  }

  //3
  override fun onCreateView(inflater: LayoutInflater,
                            container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_dummy_details_one, container, false)
  }

}