import {Box, Center, Flex, Heading, Link, Spinner, Text, useColorModeValue, VStack,} from '@chakra-ui/react';
import {useQuery} from '@tanstack/react-query';
import axios from "axios";
import {exampleTechniques} from "../examples/exampleTechniques";

const fetchTechniques = async () => {
  // Check if running locally
  if (window.location.hostname === "localhost") {
    // Simulate a network request by returning a promise that resolves with example data
    return new Promise(resolve => {
      setTimeout(() => resolve(exampleTechniques), 500); // Simulate network delay
    });
  }

  // Fetch data from the API if not local
  const {data} = await axios.get('http://localhost:8080/api/techniques');
  return data;
};

type Technique = {
  title: string
  description: string
  videoLinks: string[]
  id: string
}

export const OverviewPage = () => {
  const cardColor = useColorModeValue('white', 'gray.800');
  const {data: techniques, error, isLoading} = useQuery<Technique[]>({
    queryKey: ['techniques'],
    queryFn: fetchTechniques
  });

  if (isLoading) return <Spinner position="absolute" top="50%" left="50%"/>;
  if (error) return 'An error occurred: ' + error.message;

  return (
    <VStack spacing={8} align="stretch" mt={5}>
      <Center>
        <VStack spacing={4} maxWidth="lg" w="100%">
          {techniques?.map((technique) => (
            <Box key={technique.id} p={5} shadow="md" borderWidth="1px" bg={cardColor} borderRadius="lg"
                 overflow="hidden" w="100%">
              <Heading fontSize="xl">{technique.title}</Heading>
              <Text mt={4}>{technique.description}</Text>
              <Flex mt={2} wrap="wrap" justifyContent="start">
                {technique.videoLinks.map((link, index) => (
                  <Link key={index} href={link} isExternal color="blue.500" mr={2}>
                    Video {index + 1}
                  </Link>
                ))}
              </Flex>
            </Box>
          ))}
        </VStack>
      </Center>
    </VStack>
  );
};
