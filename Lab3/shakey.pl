% actions

act(goBetweenRooms(Room, Room2),
    [in(shakey, Room), connection(Room, Room2)], %prerequisites
    [in(shakey, Room)], %delete
    [in(shakey, Room2)] % add
    ).

act(switchLightOff(Switch),
    [in(shakey, Room), in(Switch, Room), status(Switch, on), under(Box, yes)],
    [status(Switch, on)],
    [status(Switch, off)]
    ).

act(moveBox(Box),
    [in(shakey, Room), in(Switch, Room), in(Box, Room), under(Box, no)],
    [under(Box, no)],
    [under(Box, yes)]
    ).

goal_state(
    [
        in(shakey, room1),
        status(switch1, off)
        %under(box1, yes)
        %in(box2, room2)
    ]

    ).     

initial_state(
    [
        
        on(shakey, floor),
        in(switch3, room3),
        status(switch3, on),

        in(switch4, room4),
        status(switch4, on),

        in(shakey, room3),
        room(room1),
        room(room2),
        room(room3),
        room(room4),
        room(corridor),

        in(box1, room1),
        in(box2, room1),
        in(box3, room1),
        in(box4, room1),

        in(switch1, room1),
        status(switch1, on),
        %status(status1),

        in(switch2, room2),
        status(switch2, on),

        switch(switch1),
        switch(switch2),
        switch(switch3),
        switch(switch4),

        status(switch1, on),

        under(box1, no),
        under(box2, no),
        under(box3, no),
        under(box4, no),
        box(box1),
        box(box2),
        box(box3),
        box(box4),

        connection(room1, corridor),
        connection(room2, corridor),
        connection(room3, corridor),
        connection(room4, corridor),
        connection(corridor, room1),
        connection(corridor, room2),
        connection(corridor, room3),
        connection(corridor, room4)


    ]

    ).   