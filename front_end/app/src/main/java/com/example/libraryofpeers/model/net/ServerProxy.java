package com.example.libraryofpeers.model.net;

import com.example.libraryofpeers.model.net.communication.Request;
import com.example.libraryofpeers.model.net.communication.Response;

/**
 * ServerProxy class will contain all the code having to do with accessing the database
 */
public class ServerProxy {
    /**
     * Registers a new user
     * @param request Request object containing new users information
     * @return Response object containing the result of the API request
     */
    public Response register(Request request) {
        return new Response();
    }

    /**
     * Logs in an existing user
     * @param request Request object containing the users login information
     * @return Response object containing the result of the API request
     */
    public Response login(Request request) {
        return new Response();
    }

    /**
     * Logs out a logged in user
     * @param request Request object containing the necessary info
     * @return Response object containing the result of the API request
     */
    public Response signOut(Request request) {
        return new Response();
    }

    /**
     * Sends a friend request from one user to another
     * @param request Request object containing the necessary information about both users
     * @return Response object containing the result of the API request
     */
    public Response requestFriend(Request request) {
        return new Response();
    }

    /**
     * Sends a response (confirm or deny) to a friend request that a user received
     * @param request Request object containing the response and the info about both users
     * @return Response object containing the result of the API request
     */
    public Response respondToFriendRequest(Request request) {
        return new Response();
    }

    /**
     * Retrieves an item from the database
     * @param request Request object containing necessary information about the item
     * @return Response object containing the Item and any other information about the result of the
     * API request
     */
    public Response getItem(Request request) {
        return new Response();
    }

}
