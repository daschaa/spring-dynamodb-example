import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import {Box, useColorModeValue} from '@chakra-ui/react';
import {Navigation} from "./components/Navigation";
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import {OverviewPage} from "./pages/OverviewPage";
import {SubmitTechniquePage} from "./pages/SubmitTechniquePage";

export default function App() {
  const bgColor = useColorModeValue('gray.50', 'gray.900');
  const textColor = useColorModeValue('gray.500', 'gray.400');
  const queryClient = new QueryClient();

  return (
    <QueryClientProvider client={queryClient}>
    <Router>
      <Box bg={bgColor} minH="100vh">
        <Navigation color={textColor}/>
        <Routes>
          <Route path="/" element={<OverviewPage/>}/>
          <Route path="/submit" element={<SubmitTechniquePage color={textColor}/>}/>
        </Routes>
        <Box as="footer" position="absolute" bottom="0" left="10" flex="1" w="full" h="16" color={textColor}>
          © 2023 MMA Techniques. All rights reserved.
        </Box>
      </Box>
    </Router>
    </QueryClientProvider>
  );
}


