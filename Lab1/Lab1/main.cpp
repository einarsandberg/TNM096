//
//  main.cpp
//  Lab1
//
//  Created by Einar Sandberg on 2016-04-19.
//  Copyright © 2016 Einar Sandberg. All rights reserved.
//
#include "Search.hpp"
#include <iostream>
using namespace std;
int main(int argc, const char * argv[]) {
    // insert code here...
    
    
    std::vector<int> startValues = {1, 0, 2, 4, 5, 3, 7, 8, 6};
    std::vector<int> endValues = {1, 2, 3, 4, 5, 6, 7, 8, 0};
    
    Board* board  = new Board();
    Board* endBoard = new Board();
    board->initTiles(startValues);
    endBoard->initTiles(endValues);
    
    
    Search* search = new Search(*board, *endBoard);

    
    board->print();
    cout << endl;
    endBoard->print();
    return 0;
    
}
