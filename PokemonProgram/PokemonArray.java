package PokemonProgram;
import java.io.*;
import java.util.*;

public class PokemonArray {
    
    public static File pokemonData = new File("PokemonProgram/pokedex.csv");
    private static Pokemon[] pokemonList = new Pokemon[151];
    
    private String[] pokemonInfo = new String[10];
    
    public static String[] getInfoFromNum(int num)
    {
        return pokemonList[num].getInfo();
    }
    
    public static String getNameFromNum(int num)
    {
        String[] temp = getInfoFromNum(num - 1);
        return temp[1];
    }
    
    
    public static Pokemon[] getArray()
    {
        return pokemonList;
    }
    
    public static void printPokemon(int num)
    {
        pokemonList[num - 1].getPokemonInfo();
    }
    
    
    
    public static void main(String[] args) {
        
        
        try
        {
            Scanner scan = new Scanner(pokemonData);
            
            scan.nextLine(); // skips he header
            
            for (int i = 0; i < 151; i++)
            {
                
                String tempMon = scan.nextLine();
           
                Pokemon newMon = new Pokemon(tempMon);
                pokemonList[i] = newMon;
            }
            
            scan.close();
        }
        
        catch(FileNotFoundException e)
        {
            System.out.println("Error: No Pokedex file found!");
        }
        
        
        
        
         Scanner input = new Scanner(System.in);
        
        int pkmNum = 0;
        
        boolean validInput = false;
        
        while (validInput == false)
        {
            System.out.print("Please enter the number of the Pokemon: ");
            
            try
            {
                int currentInput = input.nextInt();
                
                if (currentInput > 151 || currentInput < 1) // check if the pokemon is a real pokemon
                {
                    System.out.println("Error: not a valid pokemon number");
                }
                
                else
                {
                    pkmNum = currentInput;
                    validInput = true;
                }
            }
            
            catch(InputMismatchException e)
            {
                System.out.println("Error: Not a valid number pokemon number");
                input.next();
            }
        }
        
        printPokemon(pkmNum);
        Pokemon.makeRandomTeam();
        
        String[] types = {"Fire", "Water", "Grass", "Flying", "Electric", "Dragon", "Fairy", "Poion", "Psychic", "Normal", "Ghost", "Fighting", "Ice", "Rock", "Ground"};
        
        validInput = false;
        
        
        while (validInput == false)
        {
            System.out.print("Please enter a Pokemon type: ");
            String tempInput = input.next();
            
            for(int i = 0; i < types.length; i++)
            {
                if (types[i].equalsIgnoreCase(tempInput))
                {
                    validInput = true;
                }
            }
            
            if (validInput == false)
            {
                System.out.println("Error: Not a valid type.");
            }
            
            else
            {
                Pokemon.findAllType(tempInput);
            }
        }
        
        validInput = false;
        
        while (validInput == false)
        {
            System.out.print("Please enter another Pokemon type: ");
            String tempInput = input.next();
            
            for(int i = 0; i < types.length; i++)
            {
                if (types[i].equalsIgnoreCase(tempInput))
                {
                    validInput = true;
                }
            }
            
            if (validInput == false)
            {
                System.out.println("Error: Not a valid type.");
            }
            
            else
            {
                Pokemon.findAllSubType(tempInput);
            }
        }
        
        //myPokemon.getCoolStats();
        
        
    }
}