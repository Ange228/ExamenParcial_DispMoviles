package com.example.juego_colores
import android.widget.Button
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val options = resources.getStringArray(R.array.array_dialog_add_options)
        val dialogView = layoutInflater.inflate(R.layout.dialog_button, null)

        val dialog = MaterialAlertDialogBuilder(this)
            .setTitle(R.string.dialog_add_present_title)
            .setItems(options) { _, index ->

            }
            .setView(dialogView)
            .create()

        dialog.show()

        val btnIniciar = dialogView.findViewById<Button>(R.id.btn_Iniciar)
        btnIniciar.setOnClickListener {
            dialog.dismiss()
        }
    }
}
