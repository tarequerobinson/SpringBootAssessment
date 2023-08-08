import React, { useEffect, useState } from 'react';
import Card from './Card';
import '../Styles/CardsList.css';

const CardList = () => {
  const [cardsData, setCardsData] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);
  const [searchQuery, setSearchQuery] = useState('');

  const ITEMS_PER_PAGE = 10; // Set the number of items per page

  //fetch api to fecth the response of the url and convert the result to json
  const fetchData = async (page) => {
    const response = await fetch(`https://swapi.dev/api/people/?page=${page}`);
    const data = await response.json();
    // const finaldata = data.results ;
    setCardsData(data.results);

    //divides the total results by the items per page to tell how many pages are needed to display all results
    setTotalPages(Math.ceil(data.count / ITEMS_PER_PAGE));
  };

  useEffect(() => {
    fetchData(currentPage).catch((error) =>
      console.error('Error fetching data:', error)
    );
  }, [currentPage]);

  const handleSearchChange = (event) => {
    setSearchQuery(event.target.value);
  };

  const handlePreviousPage = () => {
    setCurrentPage((prevPage) => Math.max(prevPage - 1, 1));
  };

  const handleNextPage = () => {
    setCurrentPage((prevPage) => Math.min(prevPage + 1, totalPages));
  };

  // accepts cardData and converts the name property so that the search is not case sensitive 
  const filteredCards = cardsData.filter((cardData) =>
    cardData.name.toLowerCase().includes(searchQuery.toLowerCase())
  );

  return (
    <>
      <input
        id="searchBar"
        type="text"
        placeholder="Search by name..."
        value={searchQuery}
        onChange={handleSearchChange}
      />

      <div className="card-list">
        {/* Display the filtered cards */}
        {filteredCards.map((cardData, index) => (


          <Card
          key={index}
          name={cardData.name}
          homeworld={cardData.homeworld}
          vehicles={cardData.vehicles}
          starships={cardData.starships}
        />




        ))}
      </div>

      <div className="pagination">
        {/**OnClick triggers the handlePreviousPage function which changes the state of the current Page
         * the previous page button is disabled if the currentPage is equal to 1 
         */}
        <button onClick={handlePreviousPage} disabled={currentPage === 1}>
          Previous Page
        </button>
        {/** Displays which page the user is currently on  */}
        <span>{`Page ${currentPage} of ${totalPages}`}</span>

        {/**OnClick triggers the handleNextPage function which changes the state of the current Page
         * the button is disabled if the currentPage is equal to totalPages which would signal the end of the results
         */}

        <button onClick={handleNextPage} disabled={currentPage === totalPages}>
          Next Page
        </button>
      </div>
    </>
  );
};

export default CardList;
