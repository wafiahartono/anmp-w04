package test.s160419098.anmp.w04.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import test.s160419098.anmp.w04.R
import test.s160419098.anmp.w04.model.AircraftListAdapter
import test.s160419098.anmp.w04.viewmodel.AircraftListViewModel

class AircraftListFragment : Fragment() {
    private val aircraftListViewModel: AircraftListViewModel by viewModels()
    private val aircraftListAdapter = AircraftListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_aircraft_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recyclerViewAircraft).apply {
            adapter = aircraftListAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        aircraftListViewModel.aircrafts.observe(viewLifecycleOwner) { aircrafts ->
            Log.d("ObserveViewModel.Aircraft", "size: ${aircrafts.size}")
            aircraftListAdapter.updateData(aircrafts)
        }

        aircraftListViewModel.refresh()
    }
}