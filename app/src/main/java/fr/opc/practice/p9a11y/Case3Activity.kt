package fr.opc.practice.p9a11y

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import fr.opc.practice.p9a11y.databinding.ActivityCase3Binding

class Case3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityCase3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCase3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Set content descriptions for accessibility
        //binding.pseudoEdit.contentDescription = getString(R.string.pseudo_input_description)
        //binding.validateButton.contentDescription = getString(R.string.validate_button_description)

        // Gérer les changements dans le champ de texte
        binding.pseudoEdit.doOnTextChanged { text, _, _, _ ->
            text?.length?.let { textLength ->
                // Validation de la saisie : minimum 3 caractères
                val isValid = textLength > 2
                binding.validateButton.isEnabled = isValid

                // Changer la couleur de la bordure du champ de saisie
                val borderColor = if (isValid) {
                    resources.getColor(R.color.green400, theme) // Vert pour valide
                } else {
                    resources.getColor(R.color.red400, theme) // Rouge pour invalide
                }

                // Appliquer la couleur à la bordure du champ de saisie
                binding.pseudoInputLayout.boxStrokeColor = borderColor

                // Affichage ou masquage du message d'erreur avec une suggestion
                if (isValid) {
                    binding.errorMessage.visibility = android.view.View.GONE
                    binding.errorMessage.announceForAccessibility("") // Clear any previous announcements
                } else {
                    binding.errorMessage.visibility = android.view.View.VISIBLE
                    binding.errorMessage.text = getString(R.string.invalid_input_suggestion)
                    binding.errorMessage.announceForAccessibility(getString(R.string.invalid_input_suggestion))
                }

                // Ensure focus is managed correctly
                if (!isValid) {
                    binding.errorMessage.requestFocus()
                }
            }
        }

        // Ensure the validate button is accessible via keyboard
        binding.validateButton.setOnClickListener {
            // Handle the validation logic here
        }
    }
}