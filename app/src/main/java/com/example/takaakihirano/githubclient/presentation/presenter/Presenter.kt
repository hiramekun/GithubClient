package com.example.takaakihirano.githubclient.presentation.presenter

/**
 * Created by takaakihirano on 2018/02/12.
 */

/**
 * Interface representing a Presenter in a model view presenter (MVP) pattern.
 */
interface Presenter {
    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */
    fun resume()

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onPause() method.
     */
    fun pause()

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onDestroy() method.
     */
    fun destroy()
}
