#include <iostream>
#include "Tile.hpp"
#include <vector>
using namespace std;
Tile::Tile(int thePos)
{
    pos = thePos;
}
void Tile::print()
{
    cout << "Value: " << value;
    cout << "pos: " << pos;
}
void Tile::setPosition(int thePos)
{
    pos = thePos; // 0 is the empty tile
}
void Tile::setValue(int theValue)
{
    value = theValue;
}