package uk.co.massimocarli.dspagingtest.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.massimocarli.dspagingtest.R
import uk.co.massimocarli.dspagingtest.databinding.ActivityMainBinding
import uk.co.massimocarli.dspagingtest.getItemRepo


class MainActivity : AppCompatActivity() {

  lateinit var viewModel: MainViewModel
  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(
      this,
      R.layout.activity_main
    )
    val factory = RepositoryModelFactory(application, getItemRepo())
    viewModel = ViewModelProviders.of(this, factory)
      .get(MainViewModel::class.java)
    binding.setLifecycleOwner(this)
    binding.model = viewModel
    val linearLayoutManager = LinearLayoutManager(this)
    val dividerItemDecoration = DividerItemDecoration(
      this,
      linearLayoutManager.getOrientation()
    )
    recyclerView.run {
      adapter = ItemAdapter()
      layoutManager = linearLayoutManager
      addItemDecoration(dividerItemDecoration)
      hasFixedSize()
    }
    viewModel.liveData.observe(this, Observer {

    })
  }
}
