package fr.opc.practice.p9a11y

import android.os.Bundle
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import fr.opc.practice.p9a11y.databinding.ActivityCase2Binding

class Case2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityCase2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCase2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var isFavourite = false
        setFavouriteButtonIcon(isFavourite)

        binding.favouriteButton.setOnClickListener {
            isFavourite = !isFavourite
            setFavouriteButtonIcon(isFavourite)
            updateFavouriteButtonDescription(isFavourite)
        }

        binding.addRecipeToBasket.apply {
          //  contentDescription = "Add recipe to basket"
            setOnClickListener {
                Toast.makeText(this@Case2Activity, getString(R.string.recette_ajout_au_panier), Toast.LENGTH_SHORT).show()
                announceForAccessibility("Recipe added to basket")
            }
        }

        binding.recipeCard.setOnClickListener {
            // TODO navigate to recipe screen
        }

        // Adding custom accessibility actions to the recipe card to optimize TalkBack navigation
        binding.recipeCard.setAccessibilityDelegate(object : View.AccessibilityDelegate() {
            override fun onInitializeAccessibilityNodeInfo(
                host: View,
                info: AccessibilityNodeInfo
            ) {
                super.onInitializeAccessibilityNodeInfo(host, info)

                // Define a custom action for single gesture navigation (to navigate to the recipe screen)
                val navigateAction = AccessibilityNodeInfo.AccessibilityAction(
                    AccessibilityNodeInfo.ACTION_CLICK,
                    "one tap to Naviguer vers la recette"
                )

                // Add the custom action to the view's accessibility node info
                info?.addAction(navigateAction)
            }
        })

        // Handle the custom action (navigate to recipe screen) when clicked
        binding.recipeCard.apply {
            // Set content description for accessibility
            //contentDescription = "One tap to navigate to the recipe details."

            setOnClickListener {
                // Perform the desired action when card is clicked
                Toast.makeText(this@Case2Activity, "Naviguer vers la recette", Toast.LENGTH_SHORT).show()

                // Announce the action for screen readers
                announceForAccessibility("One tap to navigate to the recipe details.")

                // TODO: Replace with actual navigation code, e.g., Intent to open a new Activity
            }
        }
    }

    private fun setFavouriteButtonIcon(isFavourite: Boolean) {
        if (isFavourite) {
            binding.favouriteButton.setImageResource(R.drawable.ic_favourite_on)
        } else {
            binding.favouriteButton.setImageResource(R.drawable.ic_favourite_off)
        }
    }


    private fun updateFavouriteButtonDescription(isFavourite: Boolean) {
        // Update the contentDescription for TalkBack
        if (isFavourite) {
            binding.favouriteButton.contentDescription = getString(R.string.cd_ajouter_aux_favoris)
        } else {
            binding.favouriteButton.contentDescription = getString(R.string.cd_retirer_des_favoris)
        }
    }



}
