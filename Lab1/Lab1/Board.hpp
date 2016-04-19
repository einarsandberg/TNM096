
#include <iostream>
#include <vector>
#include "Tile.hpp"
class Board
{
    public:
        std::vector<Tile> tiles;
        Board();
        void print();
    
    
    private:
        void initTiles();
    
    
    
};