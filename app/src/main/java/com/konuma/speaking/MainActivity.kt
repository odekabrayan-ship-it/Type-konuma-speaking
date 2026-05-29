package com.konuma.speaking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.konuma.speaking.model.WritingDomain
import com.konuma.speaking.ui.screens.*
import com.konuma.speaking.ui.theme.KonuşmaspeakingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KonuşmaspeakingTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController, 
        startDestination = "splash",
        enterTransition = {
            fadeIn(animationSpec = tween(500, easing = FastOutSlowInEasing)) +
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(500, easing = FastOutSlowInEasing)
            )
        },
        exitTransition = {
            fadeOut(animationSpec = tween(500, easing = FastOutSlowInEasing)) +
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(500, easing = FastOutSlowInEasing)
            )
        },
        popEnterTransition = {
            fadeIn(animationSpec = tween(500, easing = FastOutSlowInEasing)) +
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(500, easing = FastOutSlowInEasing)
            )
        },
        popExitTransition = {
            fadeOut(animationSpec = tween(500, easing = FastOutSlowInEasing)) +
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(500, easing = FastOutSlowInEasing)
            )
        }
    ) {
        composable("splash") {
            SplashScreen(onTimeout = {
                navController.navigate("auth") {
                    popUpTo("splash") { inclusive = true }
                }
            })
        }

        composable("auth") {
            AuthScreen(onAuthSuccess = {
                navController.navigate("categories") {
                    popUpTo("auth") { inclusive = true }
                }
            })
        }

        composable("categories") {
            CategoryListScreen(
                onCategoryClick = { category ->
                    navController.navigate("subcategories/${category.id}")
                },
                onGlobalSearchClick = { /* Handled in overlay */ },
                onWritingClick = {
                    navController.navigate("writing_levels")
                },
                onFavoritesClick = {
                    navController.navigate("favorites")
                }
            )
        }
        
        composable("favorites") {
            FavoritesScreen(onBackClick = { navController.popBackStack() })
        }

        composable("writing_levels") {
            WritingLevelScreen(
                onLevelSelected = { level ->
                    navController.navigate("writing_domains/$level")
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("writing_domains/{level}") { backStackEntry ->
            val level = backStackEntry.arguments?.getString("level") ?: "A1"
            WritingDomainScreen(
                level = level,
                onDomainSelected = { domain ->
                    navController.navigate("writing_topics/$level/${domain.name}")
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("writing_topics/{level}/{domainName}") { backStackEntry ->
            val level = backStackEntry.arguments?.getString("level") ?: "A1"
            val domainName = backStackEntry.arguments?.getString("domainName") ?: ""
            val domain = WritingDomain.valueOf(domainName)
            WritingTopicListScreen(
                level = level,
                domain = domain,
                onTopicClick = { topic ->
                    navController.navigate("writing_workspace/${topic.id}")
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("writing_workspace/{topicId}") { backStackEntry ->
            val topicId = backStackEntry.arguments?.getString("topicId") ?: ""
            WritingWorkspaceScreen(
                topicId = topicId,
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("subcategories/{categoryId}") { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId") ?: ""
            SubCategorySelectionScreen(
                categoryId = categoryId,
                onSubCategoryClick = { subCat ->
                    navController.navigate("feed/$categoryId/$subCat")
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("feed/{categoryId}/{subCategory}") { backStackEntry ->
            val categoryId = backStackEntry.arguments?.getString("categoryId") ?: ""
            val subCategory = backStackEntry.arguments?.getString("subCategory") ?: "All"
            SentenceFeedScreen(
                categoryId = categoryId,
                initialSubCategory = subCategory,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
