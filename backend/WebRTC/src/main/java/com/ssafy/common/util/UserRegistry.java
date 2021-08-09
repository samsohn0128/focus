/*
 * (C) Copyright 2014 Kurento (http://kurento.org/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.ssafy.common.util;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.socket.WebSocketSession;

/**
 * Map of users registered in the system. This class has a concurrent hash map
 * to store users, using its name as key in the map.
 * 
 * @author Boni Garcia (bgarcia@gsyc.es)
 * @author Micael Gallego (micael.gallego@gmail.com)
 * @authos Ivan Gracia (izanmail@gmail.com)
 * @since 4.3.1
 */
public class UserRegistry {

	private final ConcurrentHashMap<String, UserSession> usersByName = new ConcurrentHashMap<>();
	private final ConcurrentHashMap<String, UserSession> usersBySessionId = new ConcurrentHashMap<>();

	public void register(UserSession user) {
		usersByName.put(user.getName(), user);
		usersBySessionId.put(user.getSession().getId(), user);
//		System.out.println("===================================================================");
//		for (String userName : usersByName.keySet()) {
//			System.out.println("userName : " + userName + " is persenster?" + usersByName.get(userName).getPresenter());
//		}
//		System.out.println("===================================================================");
	}

	public ConcurrentHashMap<String, UserSession> getUsersByName() {
		return usersByName;
	}

	public ConcurrentHashMap<String, UserSession> getUsersBySessionId() {
		return usersBySessionId;
	}

	public UserSession getByName(String name) {
		System.out.println("===================================================================");
		System.out.println("getByName()");
		for (String userName : usersByName.keySet()) {
			System.out.println("userName : " + userName + " sessionId : " + usersByName.get(userName).getSession().getId());
		}
		System.out.println("===================================================================");
		return usersByName.get(name);
	}

	public UserSession getBySession(WebSocketSession session) {
		System.out.println("===================================================================");
		System.out.println("getBySession()");
		for (String userName : usersByName.keySet()) {
			System.out.println("userName : " + userName + " sessionId : " + usersByName.get(userName).getSession().getId());
		}
		System.out.println("===================================================================");
		return usersBySessionId.get(session.getId());
	}

	public boolean exists(String name) {
		return usersByName.keySet().contains(name);
	}

	public UserSession removeBySession(WebSocketSession session) {
		final UserSession user = getBySession(session);
		usersByName.remove(user.getName());
		usersBySessionId.remove(session.getId());
		System.out.println("===================================================================");
		System.out.println("removeBySession()");
		System.out.println("removed userName : " + user.getName() + " sessionId : " + session.getId());
		for (String userName : usersByName.keySet()) {
			System.out.println("userName : " + userName + " sessionId : " + usersByName.get(userName).getSession().getId());
		}
		System.out.println("===================================================================");
		return user;
	}
}