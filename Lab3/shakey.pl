act( goBetweenRooms(Room, Room2),
    [in(shakey, Room), room(Room), room(Room2), connected(Room, Room2), on(shakey, floor)],
    [in(shakey, Room)],
    [in(shakey, Room2)]
).
act( switchLightOff(Switch, Box),
    [in(shakey, Room), in(Switch, Room), lightOn(Switch), on(shakey, Box), in(Box, Room), under(Box, Switch)],
    [lightOn(Switch)],
    [lightOff(Switch)]
).

act( switchLightOn(Switch, Box),
    [in(shakey, Room), in(Switch, Room), lightOff(Switch), on(shakey, Box), in(Box, Room), under(Box, Switch)],
    [lightOff(Switch)],
    [lightOn(Switch)]
).

act( pushBoxBetweenRooms(Box, Room, Room2),
    [box(Box), in(shakey, Room), on(shakey, floor), in(Box, Room), connected(Room, Room2)],
    [in(shakey, Room), in(Box, Room)],
    [in(shakey, Room2), in(Box, Room2)]
).

act(moveBoxToSwitch(Box, Room),
    [in(shakey, Room), in(Switch, Room), in(Box, Room), on(shakey, floor), notUnder(Box, Switch)],
    [notUnder(Box, Switch)],
    [under(Box, Switch)]
    ).


act( climbOnBox(Box),
     [box(Box),on(shakey, floor), in(shakey, Room), in(Box, Room)],
     [on(shakey, floor)],
     [on(shakey, Box)]
).

act( climbOffBox(Box),
     [box(Box),on(shakey, Box), in(shakey, Room), in(Box, Room)],
     [on(shakey, Box)],
     [on(shakey, floor)]
).

goal_state( 
    [
        in(shakey, room1),
        lightOff(switch1),
        in(box2, room2)
    ] 
    ).

initial_state( [
        in(shakey, room3),
        in(box1, room1),
        in(box2, room1),
        in(box3, room1),
        in(box4, room1),
        
        in(switch1, room1),
        in(switch2, room2),
        in(switch3, room3),
        in(switch4, room4),
        on(shakey, floor),
        room(room1),
        room(room2),
        room(room3),
        room(room4),
        room(corridor),

        connected(room1, corridor),
        connected(corridor, room1),
        connected(room2, corridor),
        connected(corridor, room2),
        connected(room3, corridor),
        connected(corridor, room3),
        connected(room4, corridor),

        notUnder(box1, switch1),
        notUnder(box2, switch1),
        notUnder(box3, switch1),
        notUnder(box4, switch1),

        lightOn(switch1),
        lightOn(switch2),
        lightOn(switch3),
        lightOn(switch4),

        box(box1),
        box(box2),
        box(box3),
        box(box4),

        switch(switch1),
        switch(switch2),
        switch(switch3),
        switch(switch4)
    ]). 