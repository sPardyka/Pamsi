#include <iostream>
#include <cstdlib>
#include <ctime>

using namespace std;

void view_array(short T[], int array_size)
{
    for (int i = 0; i < array_size; i++)
    {
        cout<<T[i]<<" ";
    }
}

void swap_values (short T[], int first_value, int second_value)
{
    int temp = T[first_value];
    T[first_value]=T[second_value];
    T[second_value]=temp;
}

void check_heap (short Tab[], int array_size, int parent)
{
    int highest_value = parent;
    int left_child = parent*2+1;
    int right_child = parent*2+2;
    if((left_child < array_size) && (Tab[left_child] > Tab[highest_value]))
    {
        highest_value = left_child;
    }
    if((right_child < array_size) && ( Tab[right_child] > Tab[highest_value]))
    {
        highest_value = right_child;
    }
    if (highest_value != parent)
    {
        swap_values(Tab, highest_value, parent);
        check_heap(Tab, array_size, highest_value);
    }
}

void sort_array (short Tab[], int array_size)
{
    int last_parrent=array_size/2-1;
    for (int i=last_parrent; i>=0 ; i--)
    {
        check_heap(Tab, array_size, i);
    }
    for ( int i=array_size-1; i > 0; i--)
    {
        swap_values(Tab, 0, i);
        //array_size--;
        check_heap(Tab, --array_size, 0);
    }
}

void reverse_sort (short T[], int array_size)
{
    int temporary_array[array_size];
    for(int i = 0; i < array_size; i++)
    {
        temporary_array[i]=T[i];
    }
    for (int i = 0; i<=array_size; i++)
    {
        T[i]=temporary_array[array_size-1-i];
    }
}

int check_sorted_heap (short T[], int array_size)
{
    for(int i=0; i<array_size-1; i++)
    {
        if(T[i]>T[i+1])
        {
            return 0;
        }
    }
    return 1;
}

int main()
{
    int array_size = 500000;
    char type;
    int validation = 0;
    srand(time(NULL));
    short heap[array_size];
    for (int i=0; i < array_size; i++)
    {
        heap[i]=rand()%10000;
    }
    cout<<"Do you want to set sort type to descending (Current sort type is ascending)? y/n: ";
    cin>>type;
    clock_t start = clock();
    sort_array(heap, array_size);
    int time=clock()-start;
    validation=check_sorted_heap(heap, array_size);
    if ((validation==1) && (type == 'n'))
    {
        cout<<"Sorting complete. Time: "<<time<<"ms";
        cout<<endl;
    }
    else if ((validation==1) && (type == 'y'))
    {
        reverse_sort(heap,array_size);
        cout<<"Sortin complete. Time: "<<time<<"ms";
        cout<<endl;
    }
    else if ((type!='y') && (type!='n'))
    {
        cout<<"Wrong type input!"<<endl;
    }
    else
    {
        cout<<"Something went wrong!"<<endl;
    }
    //view_array(heap, array_size);
    return 0;
}
