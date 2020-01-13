// ConsoleApplication5.cpp: 定义控制台应用程序的入口点。
//

#include "stdafx.h"
#include <vector>
#include <stdexcept>
#include <iostream>
#include <map>
#include <algorithm>
using namespace std;

struct A {
	int a;
	int b;
};
bool cmp(const A &p, const A &q) {
	return p.b > q.b;
}

class Rare
{
public:
	static int nthMostRare(const std::vector<int>& elements, int n)
	{
		int size = elements.size();
		vector<A> v;
		map<int,int> m;
		int i;
		for (i = 0; i < size; i++) {
			if (m.find(i) == m.end())
				m.insert(i, 1);
			else
				m[i] += 1;
		}
		map<int, int>::iterator it;
		for (it = m.begin(); it != m.end(); it++) {
			A tmp;
			tmp.a = it->first;
			tmp.b = it->second;
			v.push_back(tmp);
		}
		sort(v.begin(),v.end(),cmp);
		return v[n].a;
	}
};

#ifndef RunTests
int main()
{
	std::vector<int> input;
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