package com.example.blogapp.data.remote.home

import com.example.blogapp.core.Result
import com.example.blogapp.data.model.Post
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class HomeScreenDataSource {

    suspend fun getLatestPosts(): Result<List<Post>> {
        var postList = mutableListOf<Post>()
        val querySnapShot = FirebaseFirestore.getInstance().collection("posts").get().await()
        for(post in querySnapShot.documents) {
            post.toObject(Post::class.java)?.let { fbPost ->
                postList.add(fbPost) }
        }
        return Result.Success(postList)
    }

}