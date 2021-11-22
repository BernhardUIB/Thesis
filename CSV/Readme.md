The CSV files are in the following format:
There are 3 segments of rows: True, False and "no isolation". 
Where True means that the distribution has been allocated friends following a uniform distribution, and false means that the distribution is a left-trunctated normal distribution.
Each row has entries calculating the average new daily cases per day for the days given by the cell with header T.
After all the "average per day" cells, there is a sum of the infected size, the number of nodes total, the number of initially infected, the number of days the simulation where ran, and finally the app uptake for the simulation on that row.
If I could suggest, computing the percentage sum/N is handy showing you what percentage of the population was infected for each set of app uptake.
