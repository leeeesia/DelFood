package t.project.delfood.ui.menu

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import t.project.delfood.adapter.DishAdapter
import t.project.delfood.databinding.FragmentMenuBinding
import t.project.delfood.viewmodel.DishViewModel


@AndroidEntryPoint
class MenuFragment : Fragment() {
    @OptIn(ExperimentalCoroutinesApi::class)
    private val viewModel: DishViewModel by activityViewModels()

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentMenuBinding.inflate(inflater, container, false)

        val adapter = DishAdapter()

        binding.dishRecyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.data.observe(viewLifecycleOwner) {
                adapter.submitList(it.dishes)
            }
        }

        return binding.root
    }
}