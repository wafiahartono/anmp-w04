package test.s160419098.anmp.w04.model

import android.icu.text.NumberFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import test.s160419098.anmp.w04.R

class AircraftListAdapter(
    private val aircrafts: MutableList<Aircraft> = mutableListOf(),
) : RecyclerView.Adapter<AircraftListAdapter.ViewHolder>() {
    private val numberFormat = NumberFormat.getNumberInstance()

    class ViewHolder(
        view: View,
    ) : RecyclerView.ViewHolder(view) {
        val textAircraftType: TextView
        val textAircraftName: TextView
        val textAircraftOrigin: TextView
        val textPerformanceTopSpeed: TextView
        val textPerformanceCruiseSpeed: TextView
        val textPerformanceRange: TextView
        val textFeatures: TextView

        init {
            textAircraftType = view.findViewById(R.id.textAircraftType)
            textAircraftName = view.findViewById(R.id.textAircraftName)
            textAircraftOrigin = view.findViewById(R.id.textAircraftCountry)
            textPerformanceTopSpeed = view.findViewById(R.id.textPerformanceTopSpeed)
            textPerformanceCruiseSpeed = view.findViewById(R.id.textPerformanceCruiseSpeed)
            textPerformanceRange = view.findViewById(R.id.textPerformanceRange)
            textFeatures = view.findViewById(R.id.textAircraftFeatures)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_aircraft, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = aircrafts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            val aircraft = aircrafts[position]

            textAircraftType.text =
                aircraft.type
            textAircraftName.text =
                "${aircraft.manufacturer} ${aircraft.name}"
            textAircraftOrigin.text =
                "${aircraft.country}. First flight in ${aircraft.firstFlight}, introduced in ${aircraft.introduced}"
            textPerformanceTopSpeed.text =
                "Top speed: ${numberFormat.format(aircraft.performance!!.topSpeed)} km/s"
            textPerformanceCruiseSpeed.text =
                "Cruise speed: ${numberFormat.format(aircraft.performance!!.cruiseSpeed)} km/s"
            textPerformanceRange.text =
                "Range: ${numberFormat.format(aircraft.performance!!.range)} km"
            textFeatures.text =
                aircraft.features.joinToString(", ")
        }
    }

    fun updateData(data: List<Aircraft>) {
        aircrafts.clear()
        aircrafts.addAll(data)
        notifyDataSetChanged()
    }
}