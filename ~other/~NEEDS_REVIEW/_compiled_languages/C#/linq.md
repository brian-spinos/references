# Linq example

```cs
using System;
using System.Linq;

class Example { 
    static void Main(string[] args){
        
        //------------- Example 1
       
        int[] numbers = { 5, 4, 1, 3, 9, 8, 6, 7, 2, 0 }; 
    
        var lowNums = 
            from n in numbers 
            where n < 5
            select n;
    
        Console.WriteLine("Numbers < 5:"); 

        foreach(var x in lowNums){ 
            Console.WriteLine(x); 
        } 
        
        //------------- Example 2
        
        string[] words = { "blueberry", "chimpanzee", "abacus", "banana", "apple", "cheese" }; 
      
        var wordGroups = 
            from w in words 
            group w by w[0] into g 
            select new { FirstLetter = g.Key, Words = g }; 
      
        foreach(var g in wordGroups){ 
            Console.WriteLine("Words that start with the letter '{0}':", g.FirstLetter); 
            foreach (var w in g.Words){ 
                Console.WriteLine(w); 
            } 
        } 
        
        //------------- Example 3
        
        string[] colors = { "red", "green", "orange", "black", "white", "yellow", "yellow", "yellow" }; 
        
        var uniqueColors = colors.Select(q => q.ToUpper()).Distinct().ToList();
         
        foreach(var c in uniqueColors){ 
            Console.WriteLine("{0}", c);
        }
        
        //-------------
        
    }
}
```
