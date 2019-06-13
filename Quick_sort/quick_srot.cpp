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

void quick_sort (short T[], int f_index, int s_index)
{
    if (f_index >= s_index)
    {
        return;
    }
    else
    {
        int reference_character = T[s_index];
        int axis = f_index-1;
        int i = f_index;
        while (i < s_index)
        {
            if (T[i] < reference_character)
            {
                axis++;
                if(axis != i)
                {
                    swap_values(T,i,axis);
                }
            }
            i++;
        }
        axis++;
        if (axis != s_index)
        {
            swap_values(T,axis, s_index);
        }
        if (axis-1 < s_index - axis-1)
        {
            quick_sort(T,f_index,axis-1);
            quick_sort(T, axis+1, s_index);

        }
        else
        {
            quick_sort(T, axis+1, s_index);
            quick_sort(T,f_index,axis-1);
        }
    }

}

void sort_array (short T[], int array_size)
{
    if (array_size==0)
    {
        cout<<"Array is empty"<<endl;
    }
    else
    {
        quick_sort(T,0,array_size-1);
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

int check_sorted_array (short T[], int array_size)
{
    int temp;
    for(int i=0; i<array_size-1; i++)
    {
        temp=T[i];
        if(temp>T[i+1])
        {
            return 0;
        }
    }
    return 1;
}

int main()
{
    int array_size = 1000000;
    char type;
    int validation = 0;
    srand(time(NULL));
    short array_to_sort[array_size];
    for (int i=0; i < array_size; i++)
    {
        array_to_sort[i]=rand() % 10000;
    }
    //cout<<"Array to sort: "<<endl;
    //view_array(array_to_sort, array_size);
    cout<<"Do you want to set sort type to descending (Current sort type is ascending)? y/n: ";
    cin>>type;
    clock_t start = clock();
    sort_array(array_to_sort, array_size);
    int time=clock()-start;
    validation=check_sorted_array(array_to_sort, array_size);
    if ((validation==1) && (type == 'n'))
    {
        cout<<"Sorting complete. Time: "<<time<<"ms";
        cout<<endl;
    }
    else if ((validation==1) && (type == 'y'))
    {
        reverse_sort(array_to_sort,array_size);
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
    return 0;
}
