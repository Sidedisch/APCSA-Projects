package PokemonProgram;
import java.io.*;


public class Pokemon
{
    private String name;
    private String type1;
    private int number, HP, attack;
    private String[] pokemonInfo = new String[10];
    
    File pokemonData = new File("PokemonProgram/pokedex.csv");
    
    
    
    public Pokemon(String pokemonStrings) // Number input
    {
        
        
        pokemonInfo = pokemonStrings.split(","); 
        this.number = Integer.parseInt(pokemonInfo[0]);
        this.name = pokemonInfo[1];
        this.type1 = pokemonInfo[2];
        this.HP = Integer.parseInt(pokemonInfo[4]);
        this.attack = Integer.parseInt(pokemonInfo[5]);
        
        
        //System.out.println("Pokemon: " + name);
    }
    
    public Pokemon(int num)
    {
        pokemonInfo = PokemonArray.getInfoFromNum(num);
        
        this.number = Integer.parseInt(pokemonInfo[0]);
        this.name = pokemonInfo[1];
        this.type1 = pokemonInfo[2];
        this.HP = Integer.parseInt(pokemonInfo[4]);
        this.attack = Integer.parseInt(pokemonInfo[5]);
        
        
    }
    
    public String[] getInfo()
    {
        return pokemonInfo;
    }
    
    
    public int getNumber()
    {
        return number;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getType1()
    {
        return type1;
    }

    public String getPokemonDescription()
    {
        return pokemonInfo[9];
    }
    

    public static void getCoolStats()
    {
        double weakestAVG = Integer.MAX_VALUE;
        String weakestName = "";
        
        double strongestAVG= Integer.MIN_VALUE;
        String strongestName = "";
        
        
        for (int i = 0; i < 151 - 1; i++)
        {
            String[] tempSplit = PokemonArray.getInfoFromNum(i);
            double tempStat = (double) ((Integer.parseInt(tempSplit[4]) + Integer.parseInt(tempSplit[5]) + Integer.parseInt(tempSplit[6]) + Integer.parseInt(tempSplit[7])) / 4.0);
            
            if (tempStat < weakestAVG)
            {
                weakestAVG = tempStat;
                weakestName = tempSplit[1];
            }
            
            else
            {
                if (tempStat > strongestAVG)
                {
                    strongestAVG = tempStat;
                    strongestName = tempSplit[1];
                }
            }
            
        }
        
        System.out.println("The Pokemon with the lowest stat average is " + weakestName + " with a " + weakestAVG + " average.");
        System.out.println("The Pokemon with the highest stat average is " + strongestName + " with a " + strongestAVG + " average.");

        
        
    }
    
    public void getPokemonInfo()
    {
        System.out.println("Your pokemon is " + getName());
        System.out.println(getName() + " is a " + getType1() + " Pokemon!");
    }
    
    
    
    public static void makeRandomTeam()
    {
        String outputTeam = "";
        
        for (int i = 0; i < 6; i++)
        {
            int randomPokemon = (int) ((Math.random() * 151) + 1);
            
            String temp = PokemonArray.getNameFromNum(randomPokemon);
            
            if(outputTeam.indexOf(temp) != -1) // No repeats
            {
                i--;
            }
            
            else
            {
                outputTeam += PokemonArray.getNameFromNum(randomPokemon) + ", ";
                
            }
            
            
            
        }
        
        outputTeam = outputTeam.substring(0, outputTeam.length() - 2);
        
        System.out.println("Your random team of 6 Pokemon is: " + outputTeam); 
    }
    
    
    
    public static void findAllType(String type)
    {
        String outputType = "";
        
        System.out.println("Finding all " + type.toUpperCase() + " type Pokemon!");
        
        
        for (int i = 0; i < 151; i++)
        {
            String[] tempFind = PokemonArray.getInfoFromNum(i);
            
            if (tempFind[2].equalsIgnoreCase(type))
            {
                outputType += tempFind[1] + ", ";
            }
        }
        
        if (!(outputType.equals("")))
        {
            outputType = outputType.substring(0,outputType.length() - 2); // Removes the last ,
            System.out.println(outputType);
        }
        
        else
        {
            System.out.println("No Pokemon has that type.");
        }
    }
    
    public static void findAllSubType(String type)
    {
        String outputType = "";
        
        System.out.println("Finding all " + type.toUpperCase() + " sub-type Pokemon!");
        
        for (int i = 0; i < 151; i++)
        {
            String[] tempFind = PokemonArray.getInfoFromNum(i);
            
            if (tempFind[3].equalsIgnoreCase(type))
            {
                outputType += tempFind[1] + ", ";
            }
        }
        
        
        if (!(outputType.equals("")))
        {
            outputType = outputType.substring(0,outputType.length() - 2); // Removes the last ,
            System.out.println(outputType);
        }
        
        else
        {
            System.out.println("No Pokemon has that subtype.");
        }
        
    }
    
    /*
    
    public int getOutputDamage(String attackType, String oppType, int attackDmg)
    {
        int outputDamage = attackDmg;
        
        if (attackType.equalsIgnoreCase("Water"))
        {
            if (oppType.equalsIgnoreCase("Fire") || oppType.equalsIgnoreCase("Rock") || oppType.equalsIgnoreCase("Ground"))
            {
                outputDamage *= 2; 
            }
            
            else if (oppType.equalsIgnoreCase("Water") || oppType.equalsIgnoreCase("Grass") || oppType.equalsIgnoreCase("Dragon"))
            {
                outputDamage /= 2;
            }
        }
        
        else if (attackType.equalsIgnoreCase("Fire"))
        {
            
        }
        
        return outputDamage;
        
        
    }
    
    public void encounterPokemon()
    {
        int randomPokemon = (int) ((Math.random() * 151) + 1);
        String encounterName = getNameFromNumber(randomPokemon);
        
        System.out.println("A wild " + encounterName + " appeared!");
        
        System.out.println(this.name + " I CHOOSE YOU!");
        
        String[] oppData = pokemonStrings[randomPokemon].split(",");
        String oppType = oppData[2];
        int tempHP = this.HP;
        int oppHP = Integer.parseInt(oppData[4]);
        int oppAttack = Integer.parseInt(oppData[5]);
        
        while (tempHP > 0 && oppHP > 0)
        {
            if (getOutputDamage(this.type, oppType, this.attack) > this.attack)
            {
                System.out.println(this.name + " attacks " + encounterName + " for " + getOutputDamage(this.type, oppType, this.attack) + ". It's super effective!");
            }
            
            else if (getOutputDamage(this.type, oppType, this.attack) < this.attack && getOutputDamage(this.type, oppType, this.attack) != 0)
            {
                System.out.println(this.name + " attacks " + encounterName + " for " + getOutputDamage(this.type, oppType, this.attack) + ". It's not very effective...");
            }
            
            else if (getOutputDamage(this.type, oppType, this.attack) == 0)
            {
                System.out.println(this.name + " attacks " + encounterName + " for " + getOutputDamage(this.type, oppType, this.attack) + ". It has no effect...");
            }
                
            }
            
            
            
        }
        
        
        
        
        
    */
    
    
}