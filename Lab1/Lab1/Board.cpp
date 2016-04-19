
#include <iostream>
#include <vector>
#include "Board.hpp"
using namespace std;
Board::Board()
{
    initTiles();
}
void Board::print()
{
    for (int i = 0; i < tiles.size(); i++)
    {
        cout << tiles.at(i).value << " ";
        if (i == 2   || i == 5 || i == 8)
            cout << endl;
    }
    
}
void Board::initTiles()
{
    bool trueRandomX= true;
    bool trueRandomY = false;
    
    // init nodes with values from 1-8
    for (int i = 0; i < 9; i++)
    {
        Tile*  tile = new Tile(i);
        tiles.push_back(*tile);
    }
    // init pos. one-dimensional for simplicity.
    //equal to course book example
    tiles.at(0).setValue(1);
    tiles.at(1).setValue(0); // value = 0 is the empty tile
    tiles.at(2).setValue(2);
    tiles.at(3).setValue(4);
    tiles.at(4).setValue(5);
    tiles.at(5).setValue(3);
    tiles.at(6).setValue(7);
    tiles.at(7).setValue(8);
    tiles.at(8).setValue(6);
    
    
    

}