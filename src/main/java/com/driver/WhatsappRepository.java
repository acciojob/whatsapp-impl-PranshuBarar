package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class WhatsappRepository {

    //Assume that each user belongs to at most one group
    //You can use the below mentioned hashmaps or delete these and create your own.
    private HashMap<Group, List<User>> groupUserMap;
    private HashMap<Group, List<Message>> groupMessageMap;
    private HashMap<Message, User> senderMap;
    private HashMap<Group, User> adminMap;
    private HashSet<String> userMobile;
    private int customGroupCount;
    private int messageId;
    private HashSet<User> userDB = new HashSet<>();
    private HashSet<Message> messageDB = new HashSet<>();
    private HashSet<Group> groupDB = new HashSet<>();




    public WhatsappRepository(){
        this.groupMessageMap = new HashMap<Group, List<Message>>();
        this.groupUserMap = new HashMap<Group, List<User>>();
        this.senderMap = new HashMap<Message, User>();
        this.adminMap = new HashMap<Group, User>();
        this.userMobile = new HashSet<>();
        this.customGroupCount = 0;
        this.messageId = 0;
    }

    public String createUser(String name, String mobile) throws Exception {
        if(userMobile.contains(mobile)){
            throw new Exception("User already exists");
        }
        userMobile.add(mobile);
        User user = new User(name, mobile);
        userDB.add(user);
        return "SUCCESS";
    }

    public Group createGroup(List<User> users){
        int count = users.size();

        //Creating the group : Deciding name of the group according to the numbers of participants(users)
        Group group;
        if(count==2){
            group = new Group(users.get(1).getName(), count);
        }
        else {
            customGroupCount++;
            group = new Group("Group " + customGroupCount, count);
        }

        //Mapping "group admin (first user of the provided list)" with the "current group"
        adminMap = new HashMap<>();
        adminMap.put(group,users.get(0));

        //Mapping user list with group
        groupUserMap = new HashMap<>();
        groupUserMap.put(group,users);

        groupDB.add(group);

        return group;
    }

    public int createMessage(String content){
        messageId++;
        Message message = new Message(messageId, content, new Date());
        messageDB.add(message);
        return messageId;
    }

    public int sendMessage(Message message, User sender, Group group) throws Exception {
        if(!groupDB.contains(group)){
            throw new Exception("Group doesn't exist");
        }
        boolean flag = false;
        for(User user : groupUserMap.get(group)){
            if (user.equals(sender)) {
                flag = true;
                break;
            }
        }
        if(!flag){
            throw new Exception("You are not allowed to send message");
        }

        senderMap.put(message,sender);

        List<Message> messageList = groupMessageMap.get(group);
        messageList.add(message);

        return messageList.size() + 1;

    }

    public String changeAdmin(User approver, User user, Group group) throws Exception{
        if(!groupDB.contains(group)){
            throw new Exception("Group doesn't exist");
        }
        if(!adminMap.get(group).equals(approver)){
            throw new Exception("Approver does not have rights");
        }

        List<User> userList = groupUserMap.get(group);
        boolean flag = false;
        for(User currentuser : userList){
            if(currentuser.equals(user)){
                flag = true;
                break;
            }
        }
        if(!flag){
            throw new Exception("User is not a participant");
        }

        adminMap.put(group,user);
        return "SUCCESS";

    }

    public int removeUser(User user){
        return 0;
    }

    public String findMessage(Date start, Date end, int K) throws Exception {
        return null;
    }

}
