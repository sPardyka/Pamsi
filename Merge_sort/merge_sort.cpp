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
    for(int i=0; i<array_size-1; i++)
    {
        if(T[i]>T[i+1])
        {
            return 0;
        }
    }
    return 1;
}

void merge_lr(short T[], short temporary_array[], int left_border, int mid, int right_border)
{
    for(int i = left_border; i<= right_border; i++)
    {
        temporary_array[i] = T[i];
    }
    int ptl = left_border;
    int ptr = mid+1;
    int ptc = left_border;

    while ((ptl <= mid) && (ptr <= right_border))
    {
        if (temporary_array[ptl] <= temporary_array[ptr])
        {
            T[ptc] = temporary_array[ptl];
            ptl++;
        }
        else
        {
            T[ptc] = temporary_array[ptr];
            ptr++;
        }
        ptc++;
    }
    while (ptl <= mid)
    {
        T[ptc]=temporary_array[ptl];
        ptc++;
        ptl++;
    }
}

void merge_sort (short T[], short temporary_array[], int left_border, int right_border)
{
    if (left_border<right_border)
    {
        int mid = (left_border+right_border)/2;
        merge_sort(T,temporary_array,left_border,mid);
        merge_sort(T,temporary_array,mid+1,right_border);
        merge_lr(T,temporary_array,left_border,mid,right_border);
    }
}

int main()
{
    int array_size = 1000000;
    char type;
    int validation = 0;
    srand(time(NULL));
    int left_border=0;
    int right_border=array_size-1;
    short array_to_sort[array_size];
    short temporary_array[array_size];
    for (int i=0; i < array_size; i++)
    {
        array_to_sort[i]=rand()%10000;
    }
    //cout<<"Array to sort: "<<endl;
    //view_array(array_to_sort, array_size);
    cout<<"Do you want to set sort type to descending (Current sort type is ascending)? y/n: ";
    cin>>type;
    clock_t start = clock();
    merge_sort(array_to_sort, temporary_array, left_border, right_border);
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
