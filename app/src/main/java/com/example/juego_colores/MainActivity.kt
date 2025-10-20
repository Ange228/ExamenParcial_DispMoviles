package com.example.juego_colores
import android.widget.Button
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var dialogView: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val options = resources.getStringArray(R.array.array_dialog_add_options)
        dialogView = layoutInflater.inflate(R.layout.dialog_button, null)

        val dialog = MaterialAlertDialogBuilder(this)
            .setTitle(R.string.dialog_add_present_title)
            .setItems(options) { _, index ->

            }
            .setView(dialogView)
            .create()

        dialog.show()

        val btnIniciar = dialogView.findViewById<Button>(R.id.btn_Iniciar)
        btnIniciar.setOnClickListener {
            val myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce)
            val interpolator = MyBounceInterpolator(0.2, 20.0)
            myAnim.interpolator = interpolator


            myAnim.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: android.view.animation.Animation?) {}

                override fun onAnimationEnd(animation: android.view.animation.Animation?) {
                    // Aquí reemplazamos el fragment después de la animación
                    dialog.dismiss()
                    supportFragmentManager.commit {
                        replace(R.id.fragment_container, GameFragment())
                    }
                }

                override fun onAnimationRepeat(animation: android.view.animation.Animation?) {}
            })

            btnIniciar.startAnimation(myAnim)

            }
        }

        fun clicked(view: View) {
            val buttonclicked: Button = dialogView.findViewById(R.id.btn_Iniciar)
            buttonclicked.text = "clicked"
            val myAnim: Animation = AnimationUtils.loadAnimation(this, R.anim.bounce)

            val interpolator = MyBounceInterpolator(0.2, 20.0)
            myAnim.interpolator = interpolator

            buttonclicked.startAnimation(myAnim)
        }
    }