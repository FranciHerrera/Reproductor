package com.example.movclase20may

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GaleriaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GaleriaFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var vista: View
    private lateinit var efecto: Button
    private lateinit var musica: Button
    private lateinit var barrVol: SeekBar
    private lateinit var barraTime: SeekBar
    private lateinit var tiempo: TextView

    private var song: MediaPlayer? = null
    private var sound: MediaPlayer? = null

    private var reproducirSong = false
    private var reproducirSound = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        vista =  inflater.inflate(R.layout.fragment_galeria, container, false)

        efecto = vista.findViewById(R.id.btnEfecto)
        musica = vista.findViewById(R.id.btnMusica)
        barrVol = vista.findViewById(R.id.sbVolumen)
        barraTime = vista.findViewById(R.id.sbReproduccion)
        tiempo = vista.findViewById(R.id.txtTiempo)

        sound = MediaPlayer.create(context, R.raw.lego)
        song = MediaPlayer.create(context, R.raw.mrpink)

        sound?.isLooping = true
        song?.isLooping = true


        efecto.setOnClickListener {
            reproducirSound = if(!reproducirSound){
                Toast.makeText(context, "Reproduciendo sonido", Toast.LENGTH_SHORT).show()
                sound?.start()
                true
            }else{
                Toast.makeText(context, "Parando sonido", Toast.LENGTH_SHORT).show()
                sound?.pause()
                false
            }
        }
        musica.setOnClickListener {
            reproducirSong = if(!reproducirSong){
                Toast.makeText(context, "Reproduciendo musica", Toast.LENGTH_SHORT).show()
                song?.start()
                true
            }else{
                Toast.makeText(context, "Parando musica", Toast.LENGTH_SHORT).show()
                song?.pause()
                false
            }
        }

        barrVol.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                song?.setVolume(progress/100.0f,progress/100.0f)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        barraTime.max = song!!.duration

        barraTime.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val max = song!!.duration/1000
                val inicio = barraTime.progress.toLong()/1000
                tiempo.text = "Duración: $inicio:$max"
                if(fromUser){
                    //Toast.makeText(context, "Fromuser", Toast.LENGTH_SHORT).show()
                    song!!.pause()
                    song!!.seekTo(progress)
                    song!!.start()
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        return vista
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GaleriaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GaleriaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}