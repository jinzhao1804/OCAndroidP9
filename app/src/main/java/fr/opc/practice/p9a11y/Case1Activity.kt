package fr.opc.practice.p9a11y

import android.os.Bundle
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.opc.practice.p9a11y.databinding.ActivityCase1Binding

class Case1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityCase1Binding
    private var quantity: Int = 0 // Start with an initial quantity
    private lateinit var quantityText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCase1Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        quantityText = findViewById(R.id.quantityText)

        // Set the initial text for quantity
        binding.quantityText.text = "$quantity"

        // Add Button click listener
        binding.addButton.setOnClickListener {
            quantity++
            binding.quantityText.text = "$quantity"
            announceQuantity(quantityText)
        }

        // Remove Button click listener
        binding.removeButton.setOnClickListener {
            if (quantity > 0) {
                quantity--
                binding.quantityText.text = "$quantity"
                announceQuantity(quantityText)
            } else {
                Toast.makeText(this, getString(R.string.impossible_d_avoir_une_quantit_n_gative), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun announceQuantity(view: View) {
        // Ensure the TextView has focus before announcing
        view.requestFocus()  // Make sure the view is focused
        // Send an accessibility event to announce the updated quantity
        val event = AccessibilityEvent.obtain(AccessibilityEvent.TYPE_ANNOUNCEMENT)
        event.text.add(getString(R.string.quantity_updated, quantity))  // Make sure this includes the updated quantity
        view.sendAccessibilityEventUnchecked(event) // Send the announcement event
    }
}
