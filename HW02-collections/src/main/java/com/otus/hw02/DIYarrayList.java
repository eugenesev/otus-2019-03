package com.otus.hw02;
import java.util.*;
import java.util.function.UnaryOperator;
import java.io.IOException;

public class DIYarrayList <T> implements List<T> {

    private final int INIT_SIZE = 10;
    //private final int CUT_RATE = 4;
    private Object[] array = new Object[INIT_SIZE];
    private int count = 0;


    //Возвращает размер коллекции
    @Override
    public int size() {
        return count;
    }


    //Проверка пустой коллекции
    @Override
    public boolean isEmpty() {
        return count==0;
        }


    //Возвращает true, если элемент o входит в коллекцию
    @Override
    public boolean contains(Object o) {
        return indexOf(o)>=0;
    }


    //Возвращает индекс первого вхождения указанного элемента
    // в этом списке или -1, если этот список не содержит элемент.
    @Override
    public int indexOf(Object o) {

        if (o == null) {
            for (int i = 0; i < count; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        }
        else {
            for (int i = 0; i < count; i++) {
                if (o.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }


    // Переписывает коллекцию в массив Objeсt-ов
    @Override
    public Object[] toArray() {
        Object[] newArray = new Object[count];
        for(int i=0; i<count; i++){
            newArray[i]=this.get(i);
        }
        return newArray;
    }


    // Переписывает коллекцию в массив тогоже типа, что и тип переданного в параметр массива
   @Override
        public <T> T[] toArray(T[] a) {
        if (a.length < count)
             return (T[]) Arrays.copyOf(array, count, a.getClass());

//        if(a.length < count) {
//            Object b[] = new Object[count];
//            for(int i=0; i<count; i++){
//                b[i]= (T) this.get(i);
//            }
//            a=(T[])b;
//         return  a;
//        }
//        else {
            for (int i = 0; i < count; i++) {
               a[i] = (T) this.get(i);
              //System.arraycopy(array, 0, a, 0, count);
//            }
        }
        return a;
    }


    //Добавляет новый элемент в конец коллекции
    @Override
    public boolean add(T t) {
        if(count == array.length-1) {
            Object[] newArray = new Object[array.length*2];
            System.arraycopy(array, 0,newArray,0,array.length);
            array=newArray;
        }
        array[count++]=t;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
    }

    //Возвращает элемент коллекции с индексом index
    @Override
    public T get(int index) {
        return (T) array[index];
    }

    //Устанавливает значение element элемента с индексом index
    @Override
    public T set(int index, T element) {
        array[index]=element;
        return (T)array[index];
    }


    //Добавляет элемент в определенное место в коллекции, сдвигая остальные элементы
    @Override
    public void add(int index, T element) {
            Object[] newArray = new Object[array.length+1];
            System.arraycopy(array, 0, newArray, 0, index);
            newArray[index]=element;
            System.arraycopy(array, index, newArray, index+1, array.length-index);
            array=newArray;
            count++;
    }

    @Override
    public T remove(int index) {

        return null;
    }


    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        ListIterator it = new ListIterator() {
            private int currentIndex = index;
            @Override
            public boolean hasNext() {
                return currentIndex < count && array[currentIndex] != null;
            }

            @Override
            public Object next() {
                return array[currentIndex++];
            }

            @Override
            public boolean hasPrevious() {
                throw new UnsupportedOperationException();
            }

            @Override
            public Object previous() {
                throw new UnsupportedOperationException();
            }

            @Override
            public int nextIndex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void set(Object o) {
                array[currentIndex-1] = o;
            }

            @Override
            public void add(Object o) {
                throw new UnsupportedOperationException();
            }
        };

        return it;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) {

    }
    // Сортировка коллекции
    @Override
    public void sort(Comparator<? super T> c) {

        Arrays.sort((T[]) array, 0, count, c);


    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }
}
