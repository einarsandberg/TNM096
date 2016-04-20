
#include <iostream>
#include <vector>
#include "Board.hpp"
using namespace std;
Board::Board()
{
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
void Board::initTiles(vector <int> values)
{
    // init tiles with positions. one-dimensional for simplicity.
    for (int i = 0; i < 9; i++)
    {
        Tile*  tile = new Tile(i);
        tiles.push_back(*tile);
    }
    //equal to course book example
    
    for (int i = 0; i < values.size(); i++)
    {
        tiles.at(i).setValue(values.at(i)); // value = 0 is the empty tile
    }
}