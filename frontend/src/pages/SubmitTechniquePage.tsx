import {Box, Button, FormControl, FormLabel, Heading, Input, Text, Textarea, VStack} from "@chakra-ui/react";

export const SubmitTechniquePage = (props: { color: string }) => <Box as="main" w="full" py={{base: 6, md: 12}}>
  <VStack spacing={4} mx="auto" maxW="container.xl" px={4}>
    <Heading as="h1" size="xl" fontWeight="bold">Submit a Technique</Heading>
    <Text mx="auto" maxW="600px" color={props.color}>
      Share your knowledge with the community by submitting your technique video below.
    </Text>
    <VStack as="form" spacing={4} mx="auto" my={8} maxW="lg">
      <FormControl id="title" isRequired>
        <FormLabel>Title</FormLabel>
        <Input/>
      </FormControl>
      <FormControl id="description" isRequired>
        <FormLabel>Description</FormLabel>
        <Textarea placeholder="Enter your description here."/>
      </FormControl>
      <FormControl id="video" isRequired>
        <FormLabel>Video URL</FormLabel>
        <Input placeholder="https://youtube.com/watch"/>
      </FormControl>
      <Button colorScheme="blue" type="submit">Submit</Button>
    </VStack>
  </VStack>
</Box>;
