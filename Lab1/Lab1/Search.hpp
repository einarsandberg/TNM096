#include "Board.hpp"
class Search
{
    public:
        Search(Board theBoard, Board theGoalBoard);
    
        void run();
    private:
        Board board;
        Board goalBoard;
    
};