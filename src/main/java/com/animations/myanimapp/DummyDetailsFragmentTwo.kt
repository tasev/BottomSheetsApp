package com.animations.myanimapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class DummyDetailsFragmentTwo : Fragment() {

  val TAG = "DummyDetailsFragmentTwo"

  companion object {

    fun newInstance(): DummyDetailsFragmentTwo {
      return DummyDetailsFragmentTwo()
    }
  }

  override fun onCreateView(inflater: LayoutInflater,
                            container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_dummy_details_two, container, false)
  }

}