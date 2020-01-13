#include <vector>
#include <stdexcept>
#include <iostream>

class Rare 
{
public:
    static int nthMostRare(const std::vector<int>& elements, int n) 
    {
        for (int i = 0; i < elements.size; i++)
        {
            stdout<<elements.begin;
            
        }
        
    }
};

#ifndef RunTests
int main()
{
    std::vector<int> input;
    // 5 
    input.push_back(5);
    input.push_back(4);
    input.push_back(3);
    input.push_back(2);
    input.push_back(1);
    input.push_back(5);
    input.push_back(4);
    input.push_back(3);
    input.push_back(2);
    input.push_back(5);
    input.push_back(4);
    input.push_back(3);
    input.push_back(5);
    input.push_back(4);
    input.push_back(5);

    int x = Rare::nthMostRare(input, 2);
    std::cout << x;
}
#endif